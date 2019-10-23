using System.Collections.Generic;

namespace AuthSample.DAL.Entities
{
    public class User
    {
        public User()
        {
            UserRoles = new List<UserRole>();
        }

        public int Id { get; set; }

        public string Email { get; set; }

        public string Password { get; set; }

        public virtual List<UserRole> UserRoles { get; set; }
    }
}
