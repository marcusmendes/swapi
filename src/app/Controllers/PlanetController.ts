import { Request, Response } from 'express';

class PlanetController {
  public store(req:Request, res:Response): Response {
    return res;
  }
}

export default new PlanetController();
