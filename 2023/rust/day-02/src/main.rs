use io::Error;
use std::fs::read_to_string;
use std::io;
use std::io::ErrorKind;
use std::str::FromStr;

fn main() {
    let result1 = solve_part01("day-02/src/input.txt");
    println!("Result of part 1 is {:?}", result1);

    let result2 = solve_part02("day-02/src/input.txt");
    println!("Result of part 2 is {:?}", result2);
}

enum Color {
    Red,
    Green,
    Blue,
}

impl FromStr for Color {
    type Err = String;

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        match s {
            "red" => Ok(Color::Red),
            "green" => Ok(Color::Green),
            "blue" => Ok(Color::Blue),
            _ => Err(format!("Invalid color: {}", s)),
        }
    }
}

struct Cube {
    color: Color,
    amount: u32,
}

struct Round {
    cubes: Vec<Cube>,
}

struct Game {
    id: u32,
    rounds: Vec<Round>,
}

impl Game {
    fn is_possible(&self) -> bool {
        self.rounds.iter().all(Self::round_is_possible)
    }

    fn round_is_possible(round: &Round) -> bool {
        round.cubes.iter().all(Self::cube_is_valid)
    }

    fn cube_is_valid(cube: &Cube) -> bool {
        match cube.color {
            Color::Red => cube.amount <= 12,
            Color::Green => cube.amount <= 13,
            Color::Blue => cube.amount <= 14,
        }
    }

    fn max_amount_of_cubes(self) -> (u32, u32, u32) {
        let mut max_red = 0;
        let mut max_green = 0;
        let mut max_blue = 0;

        for round in self.rounds {
            for cube in round.cubes {
                match cube.color {
                    Color::Red => {
                        if cube.amount > max_red {
                            max_red = cube.amount
                        }
                    }
                    Color::Green => {
                        if cube.amount > max_green {
                            max_green = cube.amount
                        }
                    }
                    Color::Blue => {
                        if cube.amount > max_blue {
                            max_blue = cube.amount
                        }
                    }
                }
            }
        }

        (max_red, max_green, max_blue)
    }
}

fn solve_part01(input_name: &str) -> io::Result<u32> {
    read_to_string(input_name)?
        .lines()
        .map(|line| {
            parse_game(line).and_then(|game| {
                if game.is_possible() {
                    Ok(game.id)
                } else {
                    Ok(0)
                }
            })
        })
        .sum()
}

fn solve_part02(input_name: &str) -> io::Result<u32> {
    read_to_string(input_name)?
        .lines()
        .map(|line| {
            parse_game(line)
                .map(Game::max_amount_of_cubes)
                .map(|(r, g, b)| r * g * b)
        })
        .sum()
}

fn parse_game(line: &str) -> io::Result<Game> {
    let index_colon = line
        .find(':')
        .ok_or(Error::new(ErrorKind::InvalidInput, "Missing colon"))?;
    let index_space = line
        .find(' ')
        .ok_or(Error::new(ErrorKind::InvalidInput, "Missing space"))? + 1;

    let id: u32 = line[index_space..index_colon]
        .parse()
        .map_err(|e| Error::new(ErrorKind::InvalidData, e))?;
    let unparsed_rounds = &line[(index_colon + 2)..];

    let rounds = unparsed_rounds.split("; ")
        .map(parse_round)
        .collect::<Result<Vec<_>, _>>()?;

    Ok(Game { id, rounds })
}

fn parse_round(round: &str) -> io::Result<Round> {
    let cubes = round.split(", ")
        .map(parse_cube)
        .collect::<Result<Vec<_>, _>>()?;

    Ok(Round { cubes })
}

fn parse_cube(cube_str: &str) -> io::Result<Cube> {
    let (amount, color) = cube_str
        .split_once(' ')
        .map(|(amount_str, color_str)| (
            amount_str.parse().map_err(|e| Error::new(ErrorKind::InvalidData, e)),
            color_str.parse().map_err(|e| Error::new(ErrorKind::InvalidData, e))
        ))
        .ok_or(Error::new(ErrorKind::InvalidInput, "Could not parse cube data"))?;

    Ok(Cube { amount: amount?, color: color? })
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn part01() {
        let result = solve_part01("src/sample.txt");
        assert_eq!(result.unwrap(), 8);
    }

    #[test]
    fn part02() {
        let result = solve_part02("src/sample.txt");
        assert_eq!(result.unwrap(), 2286);
    }
}
