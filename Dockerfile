FROM gradle:7.4.0-jdk17

WORKDIR /app

COPY . .

RUN gradle clean installBootDist

CMD ./build/install/UrlShrinker-boot/bin/UrlShrinker
