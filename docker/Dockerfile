#this is the base image we use to create our image
FROM jenkins

# Info about who created this
MAINTAINER Shristy Karki

# Get rid of the admin password setup
ENV JAVA_OPTS = "-Djenkins.install.runSetupWizard = false"

#automatically installing all pli=ugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/ install-plugins.sh </usr/share/jenkins/ref/plugins.txt
