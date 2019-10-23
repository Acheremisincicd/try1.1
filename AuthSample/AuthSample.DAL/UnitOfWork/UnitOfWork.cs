using AuthSample.DAL.EF;
using AuthSample.DAL.Entities;
using AuthSample.DAL.Interfaces;
using AuthSample.DAL.Repositories;
using Microsoft.EntityFrameworkCore;
using System;

namespace AuthSample.DAL.UnitOfWork
{
    public class UnitOfWork : IUnitOfWork
    {
        private readonly AuthSampleContext _dbContext;
        private readonly Lazy<IGenericRepository<User>> _userRepository;

        public UnitOfWork(string connectionString)
        {
            var dbContextOptionsBuilder =
                new DbContextOptionsBuilder<AuthSampleContext>()
                    .UseLazyLoadingProxies()
                    .UseSqlServer(connectionString);
            _dbContext = new AuthSampleContext(dbContextOptionsBuilder.Options);

            _userRepository = new Lazy<IGenericRepository<User>>(() => new GenericRepository<User>(_dbContext));
        }

        public IGenericRepository<User> Users => _userRepository.Value;

        public void Save()
        {
            _dbContext?.SaveChanges();
        }

        public void Dispose()
        {
            _dbContext?.Dispose();
        }
    }
}
