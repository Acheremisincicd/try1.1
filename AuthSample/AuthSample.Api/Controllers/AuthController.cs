using AuthSample.Api.Models;
using AuthSample.BL.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace AuthSample.Api.Controllers
{
    [Route("api/[controller]")]
    public class AuthController : Controller
    {
        private readonly IAuthenticationService _authenticationService;

        public AuthController(IAuthenticationService authenticationService)
        {
            _authenticationService = authenticationService;
        }

        [HttpPost("login")]
        public IActionResult Login([FromBody]LoginViewModel loginViewModel)
        {
            if (!ModelState.IsValid)
            {
                return Unauthorized();
            }

            var token = _authenticationService.Login(loginViewModel.Email, loginViewModel.Password);

            return Json(token);
        }
    }
}
