# bedrock-scanner

A simple Fabric mod to automate collecting bedrock data for use with 19MisterX98's seed cracker.

This mod **exports bedrock data to a file** that can be used with [Nether_Bedrock_Cracker](https://github.com/19MisterX98/Nether_Bedrock_Cracker) to assist in seed cracking.

## Usage

/bedrockscan <size>
- `<size>` specifies the scan radius.
- The command scans the top and bottom layers of bedrock around the player and writes the positions to a file in the `bedrockscanner` folder.

Example:
/bedrockscan 32
This will scan a 32×32 area around your player and generate the output file for use with the seed cracker.
