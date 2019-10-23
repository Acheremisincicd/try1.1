using AuthSample.DAL.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.Collections.Generic;

namespace AuthSample.DAL.EF.Configuration
{
    public class RoleConfiguration : IEntityTypeConfiguration<Role>
    {
        public void Configure(EntityTypeBuilder<Role> builder)
        {

            builder.HasData(new List<Role>
            {
                new Role
                {
                    Id = 1,
                    Name = "User"
                },
                new Role
                {
                    Id = 2,
                    Name = "Admin"
                }
            }.ToArray());
        }
    }
}
