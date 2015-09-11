using globalhack_5.GlobalWeather;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Web;

namespace globalhack_5.Models
{
    public class TestResponseWithSomeThings
    {
        private string _someValue = "This is a test value";

        public TestResponseWithSomeThings()
        {
            BasicHttpBinding binding = new BasicHttpBinding();
            EndpointAddress address = new EndpointAddress("http://www.webservicex.com/globalweather.asmx");
            GlobalWeatherSoapClient soapClient = new GlobalWeatherSoapClient(binding, address);
            _someValue = soapClient.GetWeather("Alton / St. Louis Regional", "United States");
        }

        public string someValue
        {
            get
            {
                return _someValue;
            }
            set
            {
                this._someValue = value ?? this._someValue;
            }
        }
    }
}