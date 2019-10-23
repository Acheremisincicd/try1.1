using System.Collections.Generic;

namespace AuthSample.DAL.Entities
{
    public class Role
    {
        public Role()
        {
            UserRoles = new List<UserRole>();
        }

        public int Id { get; set; }

        public string Name { get; set; }

        public virtual List<UserRole> UserRoles { get; set; }
    }
}
