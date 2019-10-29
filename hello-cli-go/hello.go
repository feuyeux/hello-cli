package main

import (
	"fmt"
	"log"
	"os"
	"runtime"

	"github.com/urfave/cli"
)

func main() {
	app := cli.NewApp()

	app.Flags = []cli.Flag{
		cli.StringFlag{
			Name:  "cpu, c",
			Value: "cpu",
			Usage: "cpu count",
		},
	}

	app.Action = func(c *cli.Context) error {
		if c.String("cpu") == "cores" {
			cores := runtime.NumCPU()
			fmt.Printf("-->This machine has %d CPU cores. \n", cores)
		}
		return nil
	}

	app.Commands = []cli.Command{
		{
			Name:    "cpu",
			Aliases: []string{"t"},
			Usage:   "cpu info",
			Subcommands: []cli.Command{
				{
					Name:  "cores",
					Usage: "cpu count",
					Action: func(c *cli.Context) error {
						cores := runtime.NumCPU()
						fmt.Printf("==>This machine has %d CPU cores. \n", cores)
						return nil
					},
				},
			},
		},
	}

	err := app.Run(os.Args)
	if err != nil {
		log.Fatal(err)
	}
}
