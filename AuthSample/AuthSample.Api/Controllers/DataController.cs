using AuthSample.Api.Configuration.Auth;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace AuthSample.Api.Controllers
{
    [Authorize]
    [Route("api/[controller]")]
    public class DataController : Controller
    {
        [HttpGet("common")]
        public IActionResult GetCommonData()
        {
            return Json($"Hello, {User.Identity.Name}");
        }

        [HttpGet("restricted")]
        [Authorize(AuthorizationPolicyType.Restricted)]
        public IActionResult GetRestrictedData()
        {
            return Json($"Hello, {User.Identity.Name}");
        }
    }
}
