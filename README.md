# SF's Anvil repair

<details>
<summary>[EN]: About the mod</summary>

A mod that adds the ability to repair an anvil. You can restore it by right-clicking the anvil with an iron ingot.

## Dependencies

- [FabricAPI](https://modrinth.com/mod/fabric-api) (Fabric)
- [Forge Config API Port](https://modrinth.com/mod/forge-config-api-port) (NeoForge/Fabric)

## Configuration

Config file - `/config/sfs_anvil_repair-common.toml`

`repairItem` - the item required to repair the anvil. (Default: `"iron_ingot"`.)</br>
`usageCost` - cost per repair. (Default: `1`.)

Example config:

```toml
["Anvil Repair Settings"]
#The item used to repair the anvil.
#Default: minecraft:iron_ingot
repairItem = "minecraft:dirt"
#The amount of the repairItem consumed per one anvil repair.
# Default: 1
# Range: 1 ~ 64
usageCost = 5
```

</details>



<details>
<summary>[RU]: Про мод</summary>

Мод, добавляющий возможность ремонта наковальни. Восстановить её можно нажав ПКМ железным слитком по наковальне.

## Зависимости

- [FabricAPI](https://modrinth.com/mod/fabric-api) (Fabric)
- [Forge Config API Port](https://modrinth.com/mod/forge-config-api-port) (NeoForge/Fabric)

## Конфигурация

Файл конфига - `/config/sfs_anvil_repair-common.toml`

`repairItem` - предмет необходимый для ремонта наковальни. (По умолчанию: `"iron_ingot"`.)</br>
`usageCost` - стоимость за один ремонт. (По умолчанию: `1`.)

Пример конфига:

```toml
["Anvil Repair Settings"]
	#The item used to repair the anvil.
	#Default: minecraft:iron_ingot
	repairItem = "minecraft:dirt"
	#The amount of the repairItem consumed per one anvil repair.
	# Default: 1
	# Range: 1 ~ 64
	usageCost = 5
```

</details>


