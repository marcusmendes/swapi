module.exports = {
    env: {
      es6: true,
      node: true,
    },
    extends: [
      'plugin:@typescript-eslint/recommended',
      'prettier/@typescript-eslint',
      'airbnb-base'
    ],
    globals: {
      Atomics: 'readonly',
      SharedArrayBuffer: 'readonly',
    },
    parser: '@typescript-eslint/parser',
    parserOptions: {
      ecmaVersion: 2018,
      sourceType: 'module',
    },
    plugins: ['@typescript-eslint'],
    rules: {
      "class-methods-use-this": "off",
      "no-param-reassign": "off",
      "camelcase": "off",
      "no-unused-vars": ["error", { "argsIgnorePattern": "next" }]
    },
    settings: {
      "import/resolver": {
        "node": {
          "paths": ["src"],
          "extensions": [".js", ".ts", ".d.ts"]
        }
      },
    },
  };