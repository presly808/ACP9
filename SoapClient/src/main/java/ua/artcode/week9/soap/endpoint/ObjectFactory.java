
package ua.artcode.week9.soap.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.artcode.week9.soap.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindUserResponse_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "findUserResponse");
    private final static QName _FindUser_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "findUser");
    private final static QName _GetAll_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "getAll");
    private final static QName _GetCurrentTimeResponse_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "getCurrentTimeResponse");
    private final static QName _CreateUserResponse_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "createUserResponse");
    private final static QName _GetAllResponse_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "getAllResponse");
    private final static QName _UserAlreadyExistsException_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "UserAlreadyExistsException");
    private final static QName _InfoFromServerResponse_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "infoFromServerResponse");
    private final static QName _CreateUser_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "createUser");
    private final static QName _GetCurrentTime_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "getCurrentTime");
    private final static QName _InfoFromServer_QNAME = new QName("http://endpoint.soap.week9.artcode.ua/", "infoFromServer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.artcode.week9.soap.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindUser }
     * 
     */
    public FindUser createFindUser() {
        return new FindUser();
    }

    /**
     * Create an instance of {@link FindUserResponse }
     * 
     */
    public FindUserResponse createFindUserResponse() {
        return new FindUserResponse();
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link GetCurrentTimeResponse }
     * 
     */
    public GetCurrentTimeResponse createGetCurrentTimeResponse() {
        return new GetCurrentTimeResponse();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link UserAlreadyExistsException }
     * 
     */
    public UserAlreadyExistsException createUserAlreadyExistsException() {
        return new UserAlreadyExistsException();
    }

    /**
     * Create an instance of {@link InfoFromServerResponse }
     * 
     */
    public InfoFromServerResponse createInfoFromServerResponse() {
        return new InfoFromServerResponse();
    }

    /**
     * Create an instance of {@link GetCurrentTime }
     * 
     */
    public GetCurrentTime createGetCurrentTime() {
        return new GetCurrentTime();
    }

    /**
     * Create an instance of {@link InfoFromServer }
     * 
     */
    public InfoFromServer createInfoFromServer() {
        return new InfoFromServer();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "findUserResponse")
    public JAXBElement<FindUserResponse> createFindUserResponse(FindUserResponse value) {
        return new JAXBElement<FindUserResponse>(_FindUserResponse_QNAME, FindUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "findUser")
    public JAXBElement<FindUser> createFindUser(FindUser value) {
        return new JAXBElement<FindUser>(_FindUser_QNAME, FindUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentTimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "getCurrentTimeResponse")
    public JAXBElement<GetCurrentTimeResponse> createGetCurrentTimeResponse(GetCurrentTimeResponse value) {
        return new JAXBElement<GetCurrentTimeResponse>(_GetCurrentTimeResponse_QNAME, GetCurrentTimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserAlreadyExistsException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "UserAlreadyExistsException")
    public JAXBElement<UserAlreadyExistsException> createUserAlreadyExistsException(UserAlreadyExistsException value) {
        return new JAXBElement<UserAlreadyExistsException>(_UserAlreadyExistsException_QNAME, UserAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InfoFromServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "infoFromServerResponse")
    public JAXBElement<InfoFromServerResponse> createInfoFromServerResponse(InfoFromServerResponse value) {
        return new JAXBElement<InfoFromServerResponse>(_InfoFromServerResponse_QNAME, InfoFromServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentTime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "getCurrentTime")
    public JAXBElement<GetCurrentTime> createGetCurrentTime(GetCurrentTime value) {
        return new JAXBElement<GetCurrentTime>(_GetCurrentTime_QNAME, GetCurrentTime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InfoFromServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.soap.week9.artcode.ua/", name = "infoFromServer")
    public JAXBElement<InfoFromServer> createInfoFromServer(InfoFromServer value) {
        return new JAXBElement<InfoFromServer>(_InfoFromServer_QNAME, InfoFromServer.class, null, value);
    }

}
