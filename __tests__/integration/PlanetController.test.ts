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

  it('deve retornar erro de validação', async () => {
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
});
