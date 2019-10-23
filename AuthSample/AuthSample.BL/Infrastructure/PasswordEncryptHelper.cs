using AuthSample.BL.Interfaces;
using System;
using System.Security.Cryptography;

namespace AuthSample.BL.Infrastructure
{
    public class PasswordEncryptHelper : IPasswordEncryptHelper
    {
        public string EncryptPassword(string password)
        {
            var saltForHash = new byte[16];
            var cryptoServiceProvider = new RNGCryptoServiceProvider();
            cryptoServiceProvider.GetBytes(saltForHash);

            var passwordHash = GeneratePasswordHash(password, saltForHash);
            var passwordHashArray = new byte[36];

            Array.Copy(saltForHash, 0, passwordHashArray, 0, 16);
            Array.Copy(passwordHash, 0, passwordHashArray, 16, 20);

            var result = Convert.ToBase64String(passwordHashArray);

            return result;
        }

        public bool VerifyPassword(string passwordInput, string passwordHash)
        {
            var sourcePasswordHash = Convert.FromBase64String(passwordHash);
            var saltForHash = new byte[16];

            Array.Copy(sourcePasswordHash, 0, saltForHash, 0, 16);

            var inputPasswordHash = GeneratePasswordHash(passwordInput, saltForHash);

            for (var i = 0; i < 20; i++)
            {
                if (sourcePasswordHash[i + 16] != inputPasswordHash[i])
                {
                    return false;
                }
            }

            return true;
        }

        private byte[] GeneratePasswordHash(string password, byte[] salt)
        {
            const int iterationsCount = 10000;
            const int hashLength = 20;

            var keyForPasswordHash = new Rfc2898DeriveBytes(password, salt, iterationsCount);

            return keyForPasswordHash.GetBytes(hashLength);
        }
    }
}
