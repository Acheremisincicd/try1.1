using AuthSample.BL.Infrastructure;
using AuthSample.BL.Interfaces;
using AuthSample.BL.Services;
using AuthSample.DAL.Interfaces;
using AuthSample.DAL.UnitOfWork;
using Microsoft.Extensions.DependencyInjection;
using System.IdentityModel.Tokens.Jwt;

namespace AuthSample.Api.Configuration.DI
{
    public static class ServiceProvider
    {
        public static void AddServices(this IServiceCollection services, string connectionString)
        {
            services.AddScoped<IUnitOfWork>(_ => new UnitOfWork(connectionString));

            services.AddScoped<IAuthenticationService, AuthenticationService>();
            services.AddScoped<IPasswordEncryptHelper, PasswordEncryptHelper>();

            services.AddScoped<JwtSecurityTokenHandler>();
        }
    }
}
