# FDA Application Finder

## Modules

### Frontend &rarr; `client`

#### Build

```sh
yarn install
yarn build
```

#### Tests

```sh
yarn test
```

#### Development: port `5173`
```sh
yarn dev
```

### Backend &rarr; `server`

#### Build

```sh
mvn clean
mvn install
```

#### Tests

```sh
mvn test
```

#### Development: port `8080`
```sh
mvn spring-boot:run
```

## API Endpoint: `GET /api/v1/drugs`

### Parameters

- `manufacturer` (required, `string`)

- `brand` (optional, `string`)

### Sample API call

`/api/v1/drugs?manufacturer=teva&brand=forte`