package com.sbaars.adventofcode2019.days;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.sbaars.adventofcode2019.common.Day;
import com.sbaars.adventofcode2019.util.DoesFileOperations;

public class Day2 implements Day, DoesFileOperations {

	public static void main(String[] args) throws IOException {
		new Day2().printParts();
	}
	
	public int part1() throws IOException {
		return execute(12, 2);
	}

	private int execute(int x, int y) throws IOException {
		int[] program = Arrays.stream(getFileAsString(new File(Day1.class.getClassLoader().getResource("day2.txt").getFile())).split(",")).mapToInt(Integer::parseInt).toArray();
		program[1] = x;
		program[2] = y;
		for(int i = 0; executeInstruction(program, i, program[i]); i+=4);
		return program[0];
	}

	private boolean executeInstruction(int[] program, int i, int instruction) {
		switch(instruction) {
			case 1: program[program[i+3]] = program[program[i+1]] + program[program[i+2]]; break;
			case 2: program[program[i+3]] = program[program[i+1]] * program[program[i+2]]; break;
			case 99: return false;
			default: throw new IllegalStateException("Something went wrong!");
		}
		
		return true;
	}

	@Override
	public int part2() throws IOException {
		for(int i = 0; i<99;i++) {
			for(int j = 0; j<99; j++) {
					if(execute(i, j) == 19690720) {
						return 100 * i + j;
					}
				}
		}
		return -1;
	}

}