using System;

namespace AuthSample.BL.Infrastructure
{
    public class ServiceException : Exception
    {
        public ServiceException(string message) : base(message)
        {
        }
    }
}
