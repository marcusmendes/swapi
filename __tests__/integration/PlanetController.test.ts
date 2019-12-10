import request from 'supertest';
import app from '../../src/app';
import '../test';

describe('PlanetController', () => {
  it('deve adicionar um planeta', async () => {
    const planet = {
      name: 'Planet 1',
      climate: 'temperate',
      terrain: 'mountains',
    };

    const res = await request(app)
      .post('/api/planet/store')
      .send(planet);

    expect(res.status).toEqual(200);

    expect(res.body).toHaveProperty('_id');
    expect(res.body).toHaveProperty('name', planet.name);
    expect(res.body).toHaveProperty('climate', planet.climate);
    expect(res.body).toHaveProperty('terrain', planet.terrain);
    expect(res.body).toHaveProperty('amountFilms', null);
  });
});
