using System.Collections.Generic;
using System.Linq;
using AuthSample.DAL.EF;
using AuthSample.DAL.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace AuthSample.DAL.Repositories
{
    public class GenericRepository<T> : IGenericRepository<T> where T : class
    {
        private readonly AuthSampleContext _dbContext;

        public GenericRepository(AuthSampleContext dbContext)
        {
            _dbContext = dbContext;
        }

        public void Add(T item)
        {
            _dbContext.Set<T>().Add(item);
        }

        public T GetItem(int id)
        {
            return _dbContext.Set<T>().Find(id);
        }

        public IEnumerable<T> GetList()
        {
            return _dbContext.Set<T>().ToList();
        }

        public IQueryable<T> Select()
        {
            return _dbContext.Set<T>().AsQueryable();
        }

        public void Update(T item)
        {
            _dbContext.Entry(item).State = EntityState.Modified;
        }

        public void Remove(T item)
        {
            _dbContext.Set<T>().Remove(item);
        }
    }
}
