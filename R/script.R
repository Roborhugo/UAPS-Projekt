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


