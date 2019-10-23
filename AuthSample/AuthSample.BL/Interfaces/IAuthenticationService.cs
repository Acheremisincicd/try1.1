using AuthSample.BL.Models;
using AuthSample.DAL.Entities;

namespace AuthSample.BL.Interfaces
{
    public interface IAuthenticationService
    {
        AuthenticationData Login(string email, string password);
    }
}
