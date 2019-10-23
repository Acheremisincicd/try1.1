namespace AuthSample.BL.Interfaces
{
    public interface IPasswordEncryptHelper
    {
        string EncryptPassword(string password);

        bool VerifyPassword(string passwordInput, string passwordHash);
    }
}
