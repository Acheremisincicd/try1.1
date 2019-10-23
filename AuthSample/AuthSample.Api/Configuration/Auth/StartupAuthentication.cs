using AuthSample.BL.Models;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.IdentityModel.Tokens;
using System.Text;

namespace AuthSample.Api.Configuration.Auth
{
    public static class StartupAuthentication
    {
        public static void AddJwtAuthentication(this IServiceCollection services, JwtConfiguration configuration)
        {
            const string adminRoleName = "Admin";

            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
                .AddJwtBearer(options =>
                {
                    options.TokenValidationParameters = new TokenValidationParameters
                    {
                        ValidateIssuer = true,
                        ValidateAudience = true,
                        ValidateLifetime = true,
                        ValidateIssuerSigningKey = true,

                        ValidIssuer = configuration.Issuer,
                        ValidAudience = configuration.Audience,
                        IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(configuration.Key))
                    };
                });

            services.AddAuthorization(options =>
            {
                options.AddPolicy(AuthorizationPolicyType.Restricted, builder => { builder.RequireRole(adminRoleName); });
            });
        }
    }
}
