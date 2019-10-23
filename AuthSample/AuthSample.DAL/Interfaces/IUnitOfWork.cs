using System;
using AuthSample.DAL.Entities;

namespace AuthSample.DAL.Interfaces
{
    public interface IUnitOfWork : IDisposable
    {
        IGenericRepository<User> Users { get; }

        void Save();
    }
}
