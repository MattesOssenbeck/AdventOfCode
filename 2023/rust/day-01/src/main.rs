use std::fs::read_to_string;
use std::collections::HashMap;

fn main() {
    let result1 = solve_part01("day-01/src/input.txt");
    println!("Result of part 1 is {}", result1);

    let result2 = solve_part02("day-01/src/input.txt");
    println!("Result of part 2 is {}", result2);
}

fn solve_part01(input_name: &str) -> u32{
    read_to_string(input_name)
        .unwrap()
        .lines()
        .map(|line| find_first_and_last_numbers(&line))
        .sum()
}

fn solve_part02(input_name: &str) -> u32{
    let written_number_replacements = HashMap::from([
        ("one", "o1e"),
        ("two", "t2o"),
        ("three", "t3e"),
        ("four", "f4r"),
        ("five", "f5e"),
        ("six", "s6x"),
        ("seven", "s7n"),
        ("eight", "e8t"),
        ("nine", "n9e")
    ]);

    read_to_string(input_name)
        .unwrap()
        .lines()
        .map(|line| replace_written_numbers_with_digits(line, &written_number_replacements))
        .map(|line| find_first_and_last_numbers(&line))
        .sum()
}

fn find_first_and_last_numbers(line: &str) -> u32 {
    let mut digits = line
        .chars()
        .filter_map(|c| c.to_digit(10));

    digits
        .next()
        .map(|n| n * 10 + digits.next_back().unwrap_or(n))
        .unwrap()
}

fn replace_written_numbers_with_digits(line: &str, replacements: &HashMap<&str, &str>) -> String{
    replacements.iter()
        .fold(
            String::from(line),
            |result, (written_number, replacement)| result.replace(written_number, replacement)
        )
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn part01() {
        let result = solve_part01("src/sample1.txt");
        assert_eq!(result, 142);
    }

    #[test]
    fn part02() {
        let result = solve_part02("src/sample2.txt");
        assert_eq!(result, 281);
    }
}