.PHONY: run native uberjar clean

NAME = clj-native
VERSION = 0.1.0-SNAPSHOT

run:	native
	./${NAME}-${VERSION}-standalone 
native:	uberjar
	native-image -jar target/uberjar/${NAME}-${VERSION}-standalone.jar
uberjar:
	lein uberjar
clean:
	lein clean
	-rm ${NAME}-${VERSION}-standalone 
