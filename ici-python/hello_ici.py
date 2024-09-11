#!/usr/bin/python3

import fire
import os
import psutil
from datetime import datetime
import pytz

def ici(space:None,time:None,status:None):
    # print("ici: space {}, time {}, status {}".format(space,time,status))
    match space:
        case "disk":
            usage = get_disk_usage(os.getcwd())
            print(f"space: {usage['total']/(1024**3):.2f}GB,{usage['used']/(1024**3):.2f}GB,{usage['free']/(1024**3):.2f}GB,{usage['percent']:.2f}%")
        case _:
            print("{} is unsupported".format(space))
    match time:
        case "zone": 
            now =datetime.now()
            timezones = pytz.country_timezones
            zone = timezones('CN')[0]
            timezone=pytz.timezone(zone)
            print(f"time: {timezone},{now}")
        case _:
            print("{} is unsupported".format(time))

    for item in status:
        match item:
            case "cpu":
                logical_cores = psutil.cpu_count(logical=True)
                physical_cores = psutil.cpu_count(logical=False)
                cpu_usage = psutil.cpu_percent(interval=1)
                print("logical_cores:{} physical_cores:{} cpu_usage:{}%".format(logical_cores,physical_cores,cpu_usage))
            case "memory":
                memory = psutil.virtual_memory()
                print("{}".format(memory))
            case _:
                print("{} is unsupported".format(status))
                    
def get_disk_usage(path='.'):
    usage = psutil.disk_usage(path)
    return {
        'total': usage.total,
        'used': usage.used,
        'free': usage.free,
        'percent': usage.percent
    }

if __name__ == "__main__":
    fire.Fire(ici)
