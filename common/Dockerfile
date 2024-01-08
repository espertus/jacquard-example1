# docker build -t yourname/quiz1-autograder .

FROM gradescope/autograder-base:ubuntu-22.04-jdk17

# Copy needed directories and files. Instructors may add to these.
COPY ../quiz1/config /autograder/config
COPY ../quiz1/gradle /autograder/gradle
COPY ../quiz1/src /autograder/src
COPY ../quiz1/gradlew.bat gradlew build.gradle config.ini /autograder
COPY run_autograder.py /autograder/run_autograder

# Update packages.
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends maven checkstyle && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Ensure that scripts are Unix-friendly and executable.
RUN dos2unix /autograder/run_autograder
RUN chmod +x /autograder/run_autograder

# Force gradle download and start daemon.
WORKDIR /autograder
RUN ./gradlew clean

# Download checkstyle library
RUN mkdir -p lib
WORKDIR lib
RUN wget https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.12.1/checkstyle-10.12.1-all.jar
WORKDIR /autograder
