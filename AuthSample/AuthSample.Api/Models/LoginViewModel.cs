using System.ComponentModel.DataAnnotations;

namespace AuthSample.Api.Models
{
    public class LoginViewModel
    {
        [Required]
        [RegularExpression(@"^([\w\.\-]+)@([\w\-]+)((\.(\w){2,3})+)$")]
        public string Email { get; set; }

        [Required]
        [MinLength(6)]
        public string Password { get; set; }
    }
}
