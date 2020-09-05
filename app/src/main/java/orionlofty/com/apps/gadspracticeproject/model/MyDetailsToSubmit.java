package orionlofty.com.apps.gadspracticeproject.model;

public class MyDetailsToSubmit {
    private String firstName, lastName, emailAddress, githubLink;

    public MyDetailsToSubmit() {
    }

    public MyDetailsToSubmit(String firstName, String lastName, String emailAddress, String githubLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.githubLink = githubLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    @Override
    public String toString() {
        return "MyDetailsToSubmit{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", gihubLink='" + githubLink + '\'' +
                '}';
    }
}
