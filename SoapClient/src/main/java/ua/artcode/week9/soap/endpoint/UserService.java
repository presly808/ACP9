
package ua.artcode.week9.soap.endpoint;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserService", targetNamespace = "http://endpoint.soap.week9.artcode.ua/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @return
     *     returns java.util.List<ua.artcode.week9.soap.endpoint.User>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.GetAllResponse")
    @Action(input = "http://endpoint.soap.week9.artcode.ua/UserService/getAllRequest", output = "http://endpoint.soap.week9.artcode.ua/UserService/getAllResponse")
    public List<User> getAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "infoFromServer", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.InfoFromServer")
    @ResponseWrapper(localName = "infoFromServerResponse", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.InfoFromServerResponse")
    @Action(input = "http://endpoint.soap.week9.artcode.ua/UserService/infoFromServerRequest", output = "http://endpoint.soap.week9.artcode.ua/UserService/infoFromServerResponse")
    public String infoFromServer(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns javax.xml.datatype.XMLGregorianCalendar
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCurrentTime", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.GetCurrentTime")
    @ResponseWrapper(localName = "getCurrentTimeResponse", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.GetCurrentTimeResponse")
    @Action(input = "http://endpoint.soap.week9.artcode.ua/UserService/getCurrentTimeRequest", output = "http://endpoint.soap.week9.artcode.ua/UserService/getCurrentTimeResponse")
    public XMLGregorianCalendar getCurrentTime();

    /**
     * 
     * @param arg0
     * @return
     *     returns ua.artcode.week9.soap.endpoint.User
     * @throws UserAlreadyExistsException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.CreateUserResponse")
    @Action(input = "http://endpoint.soap.week9.artcode.ua/UserService/createUserRequest", output = "http://endpoint.soap.week9.artcode.ua/UserService/createUserResponse", fault = {
        @FaultAction(className = UserAlreadyExistsException_Exception.class, value = "http://endpoint.soap.week9.artcode.ua/UserService/createUser/Fault/UserAlreadyExistsException")
    })
    public User createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0)
        throws UserAlreadyExistsException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns ua.artcode.week9.soap.endpoint.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findUser", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.FindUser")
    @ResponseWrapper(localName = "findUserResponse", targetNamespace = "http://endpoint.soap.week9.artcode.ua/", className = "ua.artcode.week9.soap.endpoint.FindUserResponse")
    @Action(input = "http://endpoint.soap.week9.artcode.ua/UserService/findUserRequest", output = "http://endpoint.soap.week9.artcode.ua/UserService/findUserResponse")
    public User findUser(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
