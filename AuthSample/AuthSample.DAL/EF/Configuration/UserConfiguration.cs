using AuthSample.DAL.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.Collections.Generic;

namespace AuthSample.DAL.EF.Configuration
{
    public class UserConfiguration : IEntityTypeConfiguration<User>
    {
        public void Configure(EntityTypeBuilder<User> builder)
        {
            builder.Property(x => x.Email).IsRequired();
            builder.HasIndex(x => x.Email).IsUnique();

            builder.HasData(new List<User>
            {
                new User
                {
                    Id = 1,
                    Email = "admin@gmail.com",
                    Password = "gW9dR7M8CCHSgsmYeEBYOqmQrm7Vv1rmd5OEBHVEgUil8v0B", //admin123
                },
                new User
                {
                    Id = 2,
                    Email = "user@gmail.com",
                    Password = "yFWpa005ypMtv3/LjyQ/B/c/7miGUq07ImJYVr9X1qGMALCj", //user123
                }
            }.ToArray());
        }
    }
}
