using System.Collections.Generic;
using System.Linq;

namespace AuthSample.DAL.Interfaces
{
    public interface IGenericRepository<T> where T : class
    {
        IEnumerable<T> GetList();

        IQueryable<T> Select();

        T GetItem(int id);

        void Add(T entity);

        void Update(T entity);

        void Remove(T item);
    }
}
