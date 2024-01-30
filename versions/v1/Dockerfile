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

#Copy source code and pom file.
COPY src /apps/qa/src
COPY pom.xml /apps/qa
COPY testng.xml /apps/qa
COPY smoke_testng.xml /apps/qa
COPY regression_testng.xml /apps/qa
COPY grid_testng.xml /apps/qa
COPY firefox_grid_testng.xml /apps/qa

ENV env_profile smoke
ENV env_target local
ENV env_browser firefox
ENV env_headless true


ENTRYPOINT mvn clean test -P ${env_profile} -Dtarget=${env_target} -Dbrowser=${env_browser} -Dheadless=${env_headless}
