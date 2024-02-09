FROM maven:3.8.6-jdk-11

WORKDIR /apps/qa
RUN chmod -R 777 /apps/qa

RUN apt-get -o Acquire::Check-Valid-Until=false update && apt-get install -y unzip
RUN apt-get update && \
	apt-get install -y --no-install-recommends \
	libasound2 \
	gtk-3-examples \
	libdbus-glib-1-2 \
	libx11-xcb1 \
	xvfb \
	bzip2 \
	libxtst6


# download and install the latest openjdk 21.0.2
RUN curl "https://download.java.net/java/GA/jdk21.0.2/f2283984656d49d69e91c558476027ac/13/GPL/openjdk-21.0.2_linux-x64_bin.tar.gz" | tar zxf - -C /usr/local

RUN echo 'export JAVA_HOME=/usr/local/jdk-21.0.2\nexport PATH=$JAVA_HOME/bin:$PATH' > /etc/profile.d/jdk21.sh

RUN echo "source /etc/profile.d/jdk21.sh" >> /etc/bash.bashrc

# download and install the firefox browser
RUN wget -O firefoxsetup.tar.bz2 "https://download.mozilla.org/?product=firefox-latest&os=linux64&lang=en-US"

RUN tar xvf firefoxsetup.tar.bz2 -C /opt

RUN ls /opt

RUN ln -s /opt/firefox/firefox /usr/bin/firefox

# download and install the geckodriver
ENV GECKO_HOME=/usr/local/bin/geckodriver

RUN curl -L "https://github.com/mozilla/geckodriver/releases/download/v0.34.0/geckodriver-v0.34.0-linux64.tar.gz" | tar xz -C /usr/local/bin

RUN ls /usr/local/bin
RUN ls -la /usr/local/bin
RUN chmod +x /usr/local/bin/geckodriver
RUN ls -la /usr/local/bin


ENV env_profile smoke
ENV env_target local
ENV env_browser firefox
ENV env_headless true


ENTRYPOINT mvn clean test -P ${env_profile} -Dtarget=${env_target} -Dbrowser=${env_browser} -Dheadless=${env_headless}
