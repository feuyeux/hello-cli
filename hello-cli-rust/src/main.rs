extern crate clap;
extern crate num_cpus;

use clap::{App, Arg, SubCommand};

static AUTHOR: &str = "ERIC";

fn main() {
    println!("{}", AUTHOR);
    let matches = App::new("hello-cli")
        .version("1.0.0")
        .subcommand(
            SubCommand::with_name("cpu")
                .about("cpu count")
                .arg(Arg::with_name("cores").required(false)),
        )
        .get_matches();
    match matches.subcommand() {
        ("cpu", Some(matches)) => {
            let value = matches.value_of("cores").unwrap();
            match value {
                "cores" => println!("{}", num_cpus::get()),
                _ => println!("unimplement"),
            }
        }
        _ => println!("unimplement"),
    }
}
