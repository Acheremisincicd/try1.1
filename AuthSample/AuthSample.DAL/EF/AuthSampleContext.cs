using AuthSample.DAL.EF.Configuration;
using AuthSample.DAL.Entities;
using Microsoft.EntityFrameworkCore;

namespace AuthSample.DAL.EF
{
    public class AuthSampleContext : DbContext
    {
        public AuthSampleContext(DbContextOptions<AuthSampleContext> options)
            : base(options)
        {
            Database.EnsureCreated();
        }

        public DbSet<User> Users { get; set; }

        public DbSet<Role> Roles { get; set; }

        public DbSet<UserRole> UserRoles { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new UserConfiguration());
            modelBuilder.ApplyConfiguration(new RoleConfiguration());
            modelBuilder.ApplyConfiguration(new UserRoleConfiguration());
        }
    }
}
