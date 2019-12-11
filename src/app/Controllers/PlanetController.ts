import { Request, Response } from 'express';
import * as HttpStatus from 'http-status-codes';
import swapi from '../lib/swapi';

import Planet from '../Schemas/Planet';

import AddPlanetValidation from '../Validations/AddPlanetValidation';

/**
 * Classe reponsável pela rota '/planet'
 */
class PlanetController {
  /**
   * Método responsável por listar todos os planetas
   *
   * @param req Request
   * @param res Response
   */
  public async index(req:Request, res:Response): Promise<Response> {
    const planets = await Planet.find().sort({ createdAt: -1 });
    return res.json({ planets });
  }

  /**
   * Método responsável por adicionar um novo planeta
   *
   * @param req Request
   * @param res Response
   */
  public async store(req:Request, res:Response): Promise<Response> {
    const validator = await AddPlanetValidation.validate(req);

    if (validator.errors.length > 0) {
      return res
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .json({ errors: validator.errors });
    }

    const { name, climate, terrain } = req.body;

    const existPlanet = await Planet.findOne({
      $text: { $search: name, $caseSensitive: false },
    });

    if (existPlanet) {
      return res
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .json({ errors: 'Esse planeta já foi adicionado.' });
    }

    const swPlanet = await swapi.get(`planets/?search=${name}`);
    const { results } = swPlanet.data;
    let amountFilms = null;

    if (results.length > 0) {
      const { films } = results[0];
      amountFilms = films.length;
    }

    const planet = await Planet.create({
      name,
      climate,
      terrain,
      amountFilms,
    });

    return res.json(planet);
  }

  /**
   * Método responsável por remover um planeta
   *
   * @param req Request
   * @param res Response
   */
  public async destroy(req:Request, res:Response): Promise<Response> {
    const { idPlanet } = req.params;

    try {
      await Planet.deleteOne({ _id: idPlanet });
      return res
        .json({ message: `Planet com id: ${idPlanet} removido com sucesso.` });
    } catch (err) {
      return res.status(HttpStatus.BAD_REQUEST).json({ errors: err.errors });
    }
  }

  /**
   * Método responsável por mostrar os dados de um planeta específico
   *
   * @param req Request
   * @param res Response
   */
  public async show(req:Request, res:Response): Promise<Response> {

  }
}

export default new PlanetController();
