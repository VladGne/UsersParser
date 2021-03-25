import java.util.Objects;

public class User {

    String lastName;
    String firstName;
    String email;
    String organization;
    String country;
    String roles;
    String authPapers;
    String submPapers;
    String assiPapers;

    public User( String lastName, String firstName, String email, String organization, String country, String roles, String authPapers, String submPapers, String assiPapers ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.organization = organization;
        this.country = country;
        this.roles = roles;
        this.authPapers = authPapers;
        this.submPapers = submPapers;
        this.assiPapers = assiPapers;
    }

    @Override
    public boolean equals( Object o ) {
        User user = (User) o;
        return ( getLastName().equalsIgnoreCase( user.getLastName() ) &&
                getFirstName().equalsIgnoreCase( user.getFirstName() ) ) ||
                ( getLastName().equalsIgnoreCase( user.getFirstName() ) &&
                        getFirstName().equalsIgnoreCase( user.getLastName() ));
    }

    @Override
    public int hashCode() {
        return Objects.hash( getLastName(), getFirstName() );
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization( String organization ) {
        this.organization = organization;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles( String roles ) {
        this.roles = roles;
    }

    public String getAuthPapers() {
        return authPapers;
    }

    public void setAuthPapers( String authPapers ) {
        this.authPapers = authPapers;
    }

    public String getSubmPapers() {
        return submPapers;
    }

    public void setSubmPapers( String submPapers ) {
        this.submPapers = submPapers;
    }

    public String getAssiPapers() {
        return assiPapers;
    }

    public void setAssiPapers( String assiPapers ) {
        this.assiPapers = assiPapers;
    }

    public Object[] toArray(){
        Object[] objects = new Object[9];
        objects[0] = this.getLastName();
        objects[1] = this.getFirstName();
        objects[2] = this.getEmail();
        objects[3] = this.getOrganization();
        objects[4] = this.getCountry();
        objects[5] = this.getRoles();
        objects[6] = this.getAuthPapers();
        objects[7] = this.getSubmPapers();
        objects[8] = this.getAssiPapers();

        return objects;
    }
}
