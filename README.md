# split-json
Split json from an array into seperate files streamingly.

## usage
`usage: split-json-assembly-1.0.jar [filename.json]`
## example

### example input
`source.json`
```json
[
  {
    "a":"b"
  },
  {
    "c":"d"
  }
]
```
### example output
`source.json.0`
```json
{
    "a":"b"
}
```
`source.json.1`
```json
{
    "c":"d"
}
```