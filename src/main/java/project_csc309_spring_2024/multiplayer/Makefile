OUTPUT = ./output
BUILD_FILES = $(wildcard *.java)
MAIN = project_csc309_spring_2024.multiplayer.ServerMain
JAR = math-server.jar

all: $(JAR)

$(JAR): $(BUILD_FILES)
	mkdir -p $(OUTPUT)
	javac -d $(OUTPUT) $^
	jar cfe $(JAR) $(MAIN) -C $(OUTPUT) .
	java -jar $(JAR)

clean:
	rm $(JAR)
	rm -rf $(OUTPUT)