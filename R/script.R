# Global Variables
iterations <- 600
equilibrium <- 450

# Compiling programs
# Scala compilation (List generator)
setwd("../Scala")
system("scalac Random.scala")

# Java standard compilation
setwd("../Java")
system("javac MergeSort.java")

# Java AOT-compilation
system("jaotc --output MergeSort.so MergeSort.class")

# C++ compilation
setwd("../C++")
system("g++ -o MergeSort MergeSort.cpp")

# Reset directory
setwd("../R")


# Generate new random numbers
system("scala ../Scala/Random.scala 1000 ../Scala/random.txt ../scala/partial.txt")


# Parse file into dataframe with time and iteration
parseFile <- function(path) {
	dataframe <- read.csv(path, F)
	names(dataframe) <- "time"

	indices <- 1:length(dataframe$time)
	dataframe$index <- indices
	
	dataframe[, c(2,1)]
}

# Test
#parseFile("../Java/result.txt")


# Plot dataframe
plotData <- function(data, name) {
	pdf(name)
	plot(data, type = 'l')
	dev.off()
}

# Run every program once to plot
# Interpreted Java
system("java -classpath ../Java/ -Xint MergeSort ../Scala/random.txt ../Java/resultXint.txt 600")

# JIT Java
system("java -classpath ../Java/ MergeSort ../Scala/random.txt result.txt 600")

# AOT Java
system("java -XX:+UnlockExperimentalVMOptions -XX:AOTLibrary=../Java/MergeSort.so -classpath ../Java MergeSort ../Scala/random.txt ../Java/result.txt 600")

# C++
system("../C++/MergeSort ../Scala/random.txt ../C++/result.txt 600")


# Plot every program
# Interpreted Java
plotData(parseFile("../Java/resultXint.txt"), "Java Xint.pdf")

# JIT Java
plotData(parseFile("../Java/result.txt"), "Java.pdf")

# AOT Java
plotData(parseFile("../Java/resultAOT.txt"), "Java AOT.pdf")

# C++
plotData(parseFile("../C++/result.txt"), "C++.pdf")


# Get average time of one running, iterations from start to stop. (make sure result path is R/result.txt i.e. just result.txt)
getAverage <- function(runCommand, start, stop) {
	system(runCommand)
	dataframe <- parseFile("result.txt")
	data <- dataframe$time
	mean(data[start:stop])
}

# Test
#getAverage("java -classpath ../Java/ MergeSort ../Scala/random.txt result.txt 600", 450, 600)


# Get a list of average times. See getAverage() for more info.
getAverageList <- function(runCommand, start, stop, n) {
	list <- c()
	for (i in 1:n) {
		list <- c(list, getAverage(runCommand, start, stop))
	}
	
	list
}

# Test 
#getAverageList("java -classpath ../Java/ MergeSort ../Scala/random.txt result.txt 600", 450, 600, 10)








