# Parse file into dataframe with time and iteration
parseFile <- function(path) {
	dataframe <- read.csv(path, F)
	names(dataframe) <- "time"

	indices <- 1:length(dataframe$time)
	dataframe$index <- indices
	
	dataframe
}

# Test
#parseFile("../Scala/random.txt")

# Compiling programs
# Scala (List generator)
setwd("../Scala")
system("scalac Random.scala")

# Java standard compilation
setwd("../Java")
system("javac MergeSort.java")

# Java AOT-compilation
system("jaotc --output MergeSort.so MergeSort.class")

#C++
setwd("../C++")
system("g++ -o MergeSort MergeSort.cpp")

# Reset directory
setwd("../R")
