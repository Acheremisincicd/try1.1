using AuthSample.BL.Infrastructure;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace AuthSample.Api.Infrastructure.Filters
{
    public class ExceptionHandlerFilterAttribute : ExceptionFilterAttribute
    {
        public override void OnException(ExceptionContext context)
        {
            if (context.Exception is ServiceException serviceException)
            {
                context.Result = new BadRequestObjectResult(serviceException.Message);
            }

            base.OnException(context);
        }
    }
}
