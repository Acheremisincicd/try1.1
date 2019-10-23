using AuthSample.BL.Infrastructure;
using AuthSample.BL.Interfaces;
using AuthSample.BL.Models;
using AuthSample.DAL.Entities;
using AuthSample.DAL.Interfaces;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;

namespace AuthSample.BL.Services
{
    public class AuthenticationService : IAuthenticationService
    {
        private const string UserNotFoundMessage = "User not found";
        private const string IncorrectPasswordMessage = "Incorrect password";

        private readonly IUnitOfWork _unitOfWork;
        private readonly IPasswordEncryptHelper _passwordEncryptHelper;
        private readonly JwtConfiguration _jwtConfiguration;
        private readonly JwtSecurityTokenHandler _jwtSecurityTokenHandler;

        public AuthenticationService(
            IUnitOfWork unitOfWork,
            IPasswordEncryptHelper passwordEncryptHelper,
            JwtConfiguration jwtConfiguration,
            JwtSecurityTokenHandler jwtSecurityTokenHandler)
        {
            _unitOfWork = unitOfWork;
            _passwordEncryptHelper = passwordEncryptHelper;
            _jwtSecurityTokenHandler = jwtSecurityTokenHandler;
            _jwtConfiguration = jwtConfiguration;
        }

        public AuthenticationData Login(string email, string password)
        {
            var user = _unitOfWork.Users
                .Select()
                .FirstOrDefault(u => u.Email.Equals(email, StringComparison.CurrentCultureIgnoreCase))
                ?? throw new ServiceException(UserNotFoundMessage);

            if (!_passwordEncryptHelper.VerifyPassword(password, user.Password))
            {
                throw new ServiceException(IncorrectPasswordMessage);
            }

            var token = CreateSecurityToken(user);

            return new AuthenticationData
            {
                SecurityToken = token
            };
        }

        private string CreateSecurityToken(User user)
        {
            var expirationDate = DateTime.Now.AddHours(_jwtConfiguration.TokenLifetimeHours);

            var claims = new List<Claim>
            {
                new Claim(ClaimsIdentity.DefaultNameClaimType, user.Email)
            };

            foreach (var userRole in user.UserRoles)
            {
                claims.Add(new Claim(ClaimsIdentity.DefaultRoleClaimType, userRole.Role.Name));
            }

            var jwt = CreateJwt(claims, expirationDate);
            var serializedJwtToken = _jwtSecurityTokenHandler.WriteToken(jwt);

            return serializedJwtToken;
        }

        private JwtSecurityToken CreateJwt(IEnumerable<Claim> claims, DateTime expirationDate)
        {
            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_jwtConfiguration.Key));
            var credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
            var jwt = new JwtSecurityToken(
                _jwtConfiguration.Issuer,
                _jwtConfiguration.Audience,
                expires: expirationDate,
                claims: claims,
                signingCredentials: credentials);

            return jwt;
        }
    }
}
