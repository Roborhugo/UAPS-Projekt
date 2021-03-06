#include "MergeSort.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <chrono>
#include <cstring>


int main(int argc, char** args)
{
	std::string inputFileName = args[1];
	std::string outputFileName = args[2];
	int iterations = std::stoi(args[3]);


	std::vector<int> lineValues = std::vector<int>();
	long long* executionsTimes = new long long[iterations];

	{
		std::ifstream inFile = std::ifstream(inputFileName);
		int num;
		while (inFile >> num)
		{
			lineValues.push_back(num);
		}
		inFile.close();
	}


	size_t dataSize = lineValues.size();
	int* data = new int[dataSize];
	int* dataCopy = new int[dataSize];
	for (size_t i = 0; i < dataSize; i++)
	{
		data[i] = lineValues.at(i);
	}

	for (size_t i = 0; i < iterations; i++)
	{
		std::memcpy(dataCopy, data, sizeof(int) * dataSize);
		auto start = std::chrono::high_resolution_clock::now();
		mergeSort(data, 0, dataSize - 1);
		auto end = std::chrono::high_resolution_clock::now();
		std::chrono::nanoseconds time = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start);
		executionsTimes[i] = time.count();
	}

	{
		std::ofstream outFile;
		outFile.open(outputFileName);

		for (size_t i = 0; i < iterations; i++)
		{
			outFile << executionsTimes[i] << std::endl;
		}

		outFile.close();
	}

	delete[](data);
}

void mergeSort(int* data, int l, int r)
{
	if (l >= r)
	{
		return;
	}
	int m = l + (r - l) / 2;
	mergeSort(data, l, m);
	mergeSort(data, m + 1, r);
	merge(data, l, m, r);
}

void merge(int* data, int l, int m, int r)
{
	const int leftSize = m - l + 1;
	const int rightSize = r - m;

	int* leftArr = new int[leftSize];
	int* rightArr = new int[rightSize];

	for (size_t i = 0; i < leftSize; i++)
	{
		leftArr[i] = data[l + i];
	}
	for (size_t i = 0; i < rightSize; i++)
	{
		rightArr[i] = data[m + i + 1];
	}

	int lInd = 0, rInd = 0, dInd = l;

	while (lInd < leftSize && rInd < rightSize)
	{
		if (leftArr[lInd] <= rightArr[rInd])
		{
			data[dInd] = leftArr[lInd];
			lInd++;
		}
		else
		{
			data[dInd] = rightArr[rInd];
			rInd++;
		}
		dInd++;
	}
	while (lInd < leftSize) {
		data[dInd] = leftArr[lInd];
		dInd++;
		lInd++;
	}

	delete[](leftArr);
	delete[](rightArr);
}
