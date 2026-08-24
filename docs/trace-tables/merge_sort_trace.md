# Merge Sort Trace

Sort eight campus locations alphabetically: `[UGCS, Akuafo Hall, Balme Library, Commonwealth Hall, Great Hall, Legon Hall, Pentagon, Volta Hall]`.

| Stage | Left part | Right part | Result after merge |
|---|---|---|---|
| 1 | `[UGCS]` | `[Akuafo Hall]` | `[Akuafo Hall, UGCS]` |
| 2 | `[Balme Library]` | `[Commonwealth Hall]` | `[Balme Library, Commonwealth Hall]` |
| 3 | `[Great Hall]` | `[Legon Hall]` | `[Great Hall, Legon Hall]` |
| 4 | `[Pentagon]` | `[Volta Hall]` | `[Pentagon, Volta Hall]` |
| 5 | `[Akuafo Hall, UGCS]` | `[Balme Library, Commonwealth Hall]` | `[Akuafo Hall, Balme Library, Commonwealth Hall, UGCS]` |
| 6 | `[Great Hall, Legon Hall]` | `[Pentagon, Volta Hall]` | `[Great Hall, Legon Hall, Pentagon, Volta Hall]` |
| 7 | `[Akuafo Hall, Balme Library, Commonwealth Hall, UGCS]` | `[Great Hall, Legon Hall, Pentagon, Volta Hall]` | `[Akuafo Hall, Balme Library, Commonwealth Hall, Great Hall, Legon Hall, Pentagon, UGCS, Volta Hall]` |

Each merge preserves the order inside its two sorted inputs and selects the smaller front item.