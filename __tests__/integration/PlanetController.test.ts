import request from 'supertest';
import * as HttpStatus from 'http-status-codes';
import app from '../../src/app';
import '../test';

import Planet from '../../src/app/Schemas/Planet';

describe('PlanetController', () => {
  it('deve adicionar um planeta', async () => {
    const planet = {
      name: 'Alderaan',
      climate: 'temperate',
      terrain: 'mountains',
    };

    const res = await request(app)
      .post('/api/planet/store')
      .send(planet);

    expect(res.status).toEqual(HttpStatus.OK);

    expect(res.body).toHaveProperty('_id');
    expect(res.body).toHaveProperty('name', planet.name);
    expect(res.body).toHaveProperty('climate', planet.climate);
    expect(res.body).toHaveProperty('terrain', planet.terrain);
    expect(res.body).toHaveProperty('amountFilms', 2);
  });

  it('deve retornar erro de validação ao adicionar um planeta', async () => {
    const planet = {
      name: '',
      climate: '',
      terrain: '',
    };

    const res = await request(app)
      .post('/api/planet/store')
      .send(planet);

    expect(res.status).toEqual(HttpStatus.UNPROCESSABLE_ENTITY);
    expect(res.body).toEqual({
      errors: [
        'O nome do planeta não pode ser vazio.',
        'O nome do planeta deve ter no mínimo 4 caracteres.',
        'O clima do planete não pode ser vazio.',
        'O clima do planeta deve ter no mínimo 4 caracteres.',
        'O terreno do planeta não pode ser vazio.',
        'O tipo de terreno tem que ter no mínimo 4 caracteres.',
      ],
    });
  });

  it('deve retornar erro quando o planeta já foi adicionado', async () => {
    const planet = {
      name: 'Alderaan',
      climate: 'temperate',
      terrain: 'mountains',
    };

    await Planet.create(planet);

    const res = await request(app)
      .post('/api/planet/store')
      .send(planet);

    expect(res.status).toEqual(HttpStatus.UNPROCESSABLE_ENTITY);
    expect(res.body).toEqual({
      errors: 'Esse planeta já foi adicionado.',
    });
  });

  it('deve listar todos os planetas', async () => {
    const planet = {
      name: 'Alderaan',
      climate: 'temperate',
      terrain: 'mountains',
    };

    await Planet.create(planet);

    const res = await request(app).get('/api/planet');

    expect(res.status).toEqual(HttpStatus.OK);
    expect(res.body.planets.length).toBeGreaterThan(0);
  });

  it('deve buscar um planeta pelo nome', async () => {
    const planet = {
      name: 'Dagobah',
      climate: 'murky',
      terrain: 'swamp',
    };

    await Planet.create(planet);

    const res = await request(app).get(`/api/planet?search=${planet.name}`);

    expect(res.status).toEqual(HttpStatus.OK);
    expect(res.body.planets.length).toBeGreaterThan(0);

    const { planets } = res.body;

    expect(planets[0]).toHaveProperty('name', planet.name);
    expect(planets[0]).toHaveProperty('climate', planet.climate);
    expect(planets[0]).toHaveProperty('terrain', planet.terrain);
  });

  it('deve remover um planeta pelo id', async () => {
    const planet = {
      name: 'Alderaan',
      climate: 'temperate',
      terrain: 'mountains',
    };

    const planetAdded = await Planet.create(planet);

    const res = await request(app).delete(`/api/planet/${planetAdded.id}`);

    expect(res.status).toEqual(HttpStatus.OK);
    expect(res.body)
      .toEqual({ message: `Planet com id: ${planetAdded.id} removido com sucesso.` });
  });

  it('deve retornar erro ao tentar remover um planeta com id errado', async () => {
    const res = await request(app).delete('/api/planet/1');
    expect(res.status).toEqual(HttpStatus.BAD_REQUEST);
    expect(res.body).toHaveProperty('errors');
  });

  it('deve retornar os dados do planeta pelo id', async () => {
    const planet = {
      name: 'Alderaan',
      climate: 'temperate',
      terrain: 'mountains',
      amountFilms: 2,
    };

    const planetAdded = await Planet.create(planet);

    const res = await request(app).get(`/api/planet/${planetAdded.id}`);

    expect(res.status).toEqual(HttpStatus.OK);

    expect(res.body).toHaveProperty('_id');
    expect(res.body).toHaveProperty('name', planet.name);
    expect(res.body).toHaveProperty('climate', planet.climate);
    expect(res.body).toHaveProperty('terrain', planet.terrain);
    expect(res.body).toHaveProperty('amountFilms', 2);
  });

  it('deve retorna erro ao buscar os dados de um planeta com id errado', async () => {
    const res = await request(app).get('/api/planet/2');
    expect(res.status).toEqual(HttpStatus.BAD_REQUEST);
    expect(res.body).toHaveProperty('errors');
  });
});
