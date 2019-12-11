import request from 'supertest';
import * as HttpStatus from 'http-status-codes';
import app from '../../src/app';
import '../test';

describe('DefaultController', () => {
  it('deve retornar os dados da api', async () => {
    const res = await request(app).get('/');
    expect(res.status).toEqual(HttpStatus.OK);
    expect(res.body).toEqual({ message: 'Star Wars API' });
  });
});
