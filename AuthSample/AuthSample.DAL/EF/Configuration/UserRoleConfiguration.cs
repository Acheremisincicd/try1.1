using AuthSample.DAL.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.Collections.Generic;

namespace AuthSample.DAL.EF.Configuration
{
    public class UserRoleConfiguration : IEntityTypeConfiguration<UserRole>
    {
        public void Configure(EntityTypeBuilder<UserRole> builder)
        {
            builder.HasKey(x => new { x.UserId, x.RoleId });

            builder.HasOne(x => x.User)
                .WithMany(x => x.UserRoles)
                .HasForeignKey(x => x.UserId);
            builder.HasOne(x => x.Role)
                .WithMany(x => x.UserRoles)
                .HasForeignKey(x => x.RoleId);

            builder.HasData(new List<UserRole>
            {
                new UserRole
                {
                    UserId = 1,
                    RoleId = 1
                },
                new UserRole
                {
                    UserId = 1,
                    RoleId = 2
                },
                new UserRole
                {
                    UserId = 2,
                    RoleId = 1
                }
            }.ToArray());
        }
    }
}
