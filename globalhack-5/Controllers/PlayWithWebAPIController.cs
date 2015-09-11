using globalhack_5.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.ServiceModel;
using System.Web.Http;

namespace globalhack_5.Controllers
{
    public class PlayWithWebAPIController : ApiController
    {
        public TestResponseWithSomeThings Get(string id = null)
        {
            TestResponseWithSomeThings response = new TestResponseWithSomeThings();
            return response;
        }
    }
}
