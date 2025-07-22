
```markdown
# 🐾 PetShop POS

Simple Point of Sales desktop application for pet shops built with Python Flet and MongoDB.

## Features

- 🛒 **Sales Processing** - Quick checkout and invoice generation
- 📦 **Inventory Management** - Track products and stock levels

## Quick Start

### Requirements
- Python 3.8+
- Flet 0.70.0.dev5089
- MongoDB

### Installation

1. **Clone and setup**
   ```bash
   git clone https://github.com/hendrapaiton/petshop.git
   cd petshop
   python -m venv venv
   source venv/bin/activate  # Windows: venv\Scripts\activate
   pip install -r requirements.txt
   ```

2. **Run application**
   ```bash
   python main.py
   ```

## Project Structure

```
src/
├── model/           # Data models
├── repository/      # Database operations  
├── presenter/       # Business logic
├── view/            # UI components
├── service/         # External services
└── config/          # Configuration
```

## Configuration

Edit `src/config/settings.py`:
```python
DATABASE_URL = "mongodb://localhost:27017"
DATABASE_NAME = "petshop"
```

## Contributing

1. Fork the repository
2. Create feature branch
3. Make changes
4. Submit pull request

## License

MIT License - see [LICENSE](LICENSE) file.

---

**Made for pet shop owners** 🐕🐱
```
